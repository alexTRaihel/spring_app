package com.station.app.schedule;

import com.station.app.client.YandexAddressUpdateClient;
import com.station.app.entity.Plug;
import com.station.app.repo.PlugsRepository;
import com.station.app.service.StationService;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

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
        this.updatePlugsData(53.67785,
                23.829484,
                2.0421954159703546,
                21.093749999999996);
    }

    public void updatePlugsData(Double lat,
                                Double lon,
                                Double spanLng,
                                Double spanLat){
        stationService
                .getStationsByLatitudeAndLongitude(lat, lon, spanLng, spanLat)
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
                "house"
        ).map(address -> {
            plug.setAddressFull(address);
            return plug;
        }).flux();
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
