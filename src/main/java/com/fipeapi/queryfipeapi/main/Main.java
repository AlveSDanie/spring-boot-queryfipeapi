package com.fipeapi.queryfipeapi.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fipeapi.queryfipeapi.model.AutomobileData;
import com.fipeapi.queryfipeapi.model.Models;
import com.fipeapi.queryfipeapi.model.Vehicle;
import com.fipeapi.queryfipeapi.service.ApiReader;
import com.fipeapi.queryfipeapi.service.ConvertData;
import com.fipeapi.queryfipeapi.service.UrlMaker;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private ApiReader reader = new ApiReader();
    private UrlMaker url = new UrlMaker();
    private ConvertData convert = new ConvertData();
    private Scanner scanner = new Scanner(System.in);
    String json;

    public void showMenu() {

        System.out.println("---- OPÇOES ----\n");
        System.out.println("""
                Carro
                Moto
                Caminhão
                """);
        System.out.println("Digite uma das opções para consultar valores: ");
        String automobileType = scanner.nextLine();

        switch (automobileType.toLowerCase()) {
            case "carro":
                automobileType = "carros";
                break;
            case "moto":
                automobileType = "motos";
                break;
            case "caminhão":
                automobileType = "caminhoes";
                break;
        }

        url.setAutomobile(automobileType);
        json = reader.queryApiData(url.url());

        List<AutomobileData> automobile = convert.convertJsonToObjectList(json, new TypeReference<List<AutomobileData>>() {
        });
        automobile.stream()
                .sorted(Comparator.comparing(AutomobileData::code))
                .forEach(b -> System.out.println(
                        "Cód: " + b.code() + " - Descrição: " + b.name()
                ));

        System.out.println("Informe o código da marca para consultar: ");
        String automobileBrand = scanner.nextLine();

        url.setNumberBrand(automobileBrand);
        json = reader.queryApiData(url.url());

        Models automobileModels = convert.convertJsonToObject(json, Models.class);

        automobileModels.models().stream()
                .sorted(Comparator.comparing(AutomobileData::code))
                .forEach(i -> System.out.println(
                        "Cód: " + i.code() + " - Descrição: " + i.name()
                ));

        System.out.println("\nDigite um trecho do nome do veículo a ser buscado: ");
        String partName = scanner.nextLine();
        List<AutomobileData> automobileNameFilter = automobileModels.models().stream()
                .filter(a -> a.name().toLowerCase().contains(partName.toLowerCase()))
                .collect(Collectors.toList());

        automobileNameFilter.forEach(a -> System.out.println(
                "Cód: " + a.code() + " - Descrição: " + a.name()
        ));

        System.out.println("\nInforme o código do modelo: ");
        String codModel = scanner.nextLine();
        url.setNumberModel(codModel);

        json = reader.queryApiData(url.url());
        List<AutomobileData> vehicleAvailable = convert.convertJsonToObjectList(json, new TypeReference<List<AutomobileData>>() {
        });

        List<Vehicle> vehicleList = new ArrayList<>();

        for (int i = 0; i < vehicleAvailable.size(); i++) {
            var year = vehicleAvailable.get(i).code();
            url.setNumberYear(year);
            json = reader.queryApiData(url.url());

            Vehicle vehicle = convert.convertJsonToObject(json, Vehicle.class);

            vehicleList.add(vehicle);
        }

        vehicleList.forEach(v -> System.out.println(
                "Marca: " + v.brand() +
                        " - Modelo: " + v.model() +
                        " - Ano: " + v.year() +
                        " - Combustível: " + v.fuel() +
                        " - Valor: " + v.price()
        ));

    }

}
