package ua.vkireiev.portaone;

import ua.vkireiev.portaone.service.FileService;
import ua.vkireiev.portaone.service.StatisticalService;
import ua.vkireiev.portaone.util.InputDataConverter;

/**
 *
 */
public class MainApp {

    private static final String DEFAULT_FILE_NAME = "input.txt";

    public static void main(String[] args) {

        String inputFileName = (args.length > 0)
                ? args[0]
                : DEFAULT_FILE_NAME;
        FileService fileService = new FileService();
        int[] array = InputDataConverter.convertToArray(fileService.readFile(inputFileName));

        StatisticalService statisticalService = new StatisticalService();
        statisticalService.calculateStatisticalMetrics(array);
        statisticalService.printStatisticalMetrics();
    }

}
