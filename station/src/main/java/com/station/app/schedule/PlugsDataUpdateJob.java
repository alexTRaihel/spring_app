package com.station.app.schedule;

import com.station.app.client.YandexAddressUpdateClient;
import com.station.app.entity.Plug;
import com.station.app.repo.PlugsRepository;
import com.station.app.service.StationService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@EnableScheduling
public class PlugsDataUpdateJob {

    private final StationService stationService;
    private final PlugsRepository plugsRepository;
    private final YandexAddressUpdateClient yandexAddressUpdateClient;

    public PlugsDataUpdateJob(StationService stationService,
                              PlugsRepository plugsRepository,
                              YandexAddressUpdateClient yandexAddressUpdateClient) {
        this.stationService = stationService;
        this.plugsRepository = plugsRepository;
        this.yandexAddressUpdateClient = yandexAddressUpdateClient;
//        this.updatePlugsData(52.94271406719893,
//                29.516808446831,
//                12.5244140625,
//                5.297821900759615);
    }

    public void updatePlugsData(Double lat,
                                Double lon,
                                Double spanLng,
                                Double spanLat){
        stationService
                .getStationsByLatitudeAndLongitudeFromApi(lat, lon, spanLng, spanLat)
                .subscribeOn(Schedulers.parallel())
                .flatMap(this::updateAddress)
                .flatMap(plugsRepository::save)
                .thenMany(plugsRepository.findAll())
                .subscribe(s -> System.out.println("saving " + s.getName()));
    }

    private Flux<Plug> updateAddress(Plug plug){
        return yandexAddressUpdateClient.getInfoByCoordinates(
                plug.getLatitude(),
                plug.getLongitude(),
                "ru_RU",
                2,
                "street"
        ).map(address -> {
            plug.setCountryCode(address.getCountryCode());
            plug.setLocationId(address.getCityId());
            return plug;
        }).flux();
    }

    @Async
    @Scheduled(fixedRate = 300)
    public void testScheduled(){
        System.out.println("HELLO!!!");
    }

//    userService.getFavoriteMemes(userId)
//            .timeout(Duration.ofMillis(800)) //длительность тайм-аута
//            //альтернативный источник данных
//            .onErrorResume(cacheService.cachedFavoritesFor(userId))
//            .flatMap(memeService.getMemes) //закачиваем мемы по ID
//   .switchIfEmpty(suggestionService.getSuggestedMemes())
//            .take(5) // берем первые 5 элементов
//   .publishOn(UiUtils.uiThreadScheduler())
//            .subscribe(favorites -> {
//        uiList.show(favorites);
//    }, UiUtils::errorPopup);
}
