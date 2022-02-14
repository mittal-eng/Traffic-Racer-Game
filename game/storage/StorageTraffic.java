package game.storage;

import engine.cars.CarTraffic;
import engine.ImageFiles;

/**
 * Created by Leo on 08-Oct-15.
 */
public interface StorageTraffic
{
    public static final CarTraffic TRAFFIC_PORSCHE_911_1968 = new CarTraffic(ImageFiles.CAR_PORSCHE_911_1968, 4.163, 1.613 + 0.15);
    public static final CarTraffic TRAFFIC_PONTIAC_GTO_1966 = new CarTraffic(ImageFiles.CAR_PONTIAC_GTO_1966, 5.247, 1.897);
    public static final CarTraffic TRAFFIC_FORD_MUSTANG_GT390_1968 = new CarTraffic(ImageFiles.CAR_FORD_MUSTANG_GT390_1968, 4.663, 1.801);
    public static final CarTraffic TRAFFIC_LAMBORGHINI_COUNTACH_LP400 = new CarTraffic(ImageFiles.CAR_LAMBORGHINI_COUNTACH_LP400, 4.140 + 0.2, 1.890 + 0.1);
    public static final CarTraffic TRAFFIC_FERRARI_TESTAROSSA = new CarTraffic(ImageFiles.CAR_FERRARI_TESTAROSSA, 4.485 + 0.2, 1.976);
    public static final CarTraffic TRAFFIC_BMW_M1 = new CarTraffic(ImageFiles.CAR_BMW_M1, 4.346, 1.823);
    public static final CarTraffic TRAFFIC_AUSTIN_COOPER_S = new CarTraffic(ImageFiles.TRAFFIC_AUSTIN_COOPER_S, 3.054, 2.035);
    public static final CarTraffic TRAFFIC_CITROEN_2CV_1959 = new CarTraffic(ImageFiles.TRAFFIC_CITROEN_2CV_1959, 3.86, 1.48);
    public static final CarTraffic TRAFFIC_CITROEN_DS_21_1966 = new CarTraffic(ImageFiles.TRAFFIC_CITROEN_DS_21_1966, 4.826, 1.791);
    public static final CarTraffic TRAFFIC_VOLKSWAGEN_BEETLE_1948 = new CarTraffic(ImageFiles.TRAFFIC_VOLKSWAGEN_BEETLE_1948, 4.1, 1.55);
    public static final CarTraffic TRAFFIC_ROLLS_ROYCE_SILVER_CLOUD_II_1959 = new CarTraffic(ImageFiles.TRAFFIC_ROLLS_ROYCE_SILVER_CLOUD_II_1959, 5.41, 1.89);
}
