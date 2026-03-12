package com.example.lab1_tos;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 1. Запуск разложения cos(x)
        double x = Math.PI / 3;
        double result = CosineFunction.cosSeries(x);
        System.out.println("cos(" + x + ") = " + result);

        // 2. Запуск сортировки Шелла
        int[] array = {5, 2, 9, 1, 5, 6};
        System.out.println("До сортировки: " + java.util.Arrays.toString(array));
        ShellSort.shellSort(array);
        System.out.println("После сортировки: " + java.util.Arrays.toString(array));

        // 3. Запуск доменной модели
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Person arthur = new DomainModel.Person("Артур");

        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(speaker);
        crowd.addPerson(arthur);

        DomainModel.Object stage = new DomainModel.Object("Помост", "Второй этаж здания", "Stage");

        DomainModel.Building building = new DomainModel.Building("Здание с помостом", "Второй этаж");

        DomainModel.Event event = new DomainModel.Event(speaker, crowd, stage, building);
        System.out.println("Событие произошло с оратором " + event.getSpeaker().getName() +
                ", толпой из " + event.getCrowd().getSize() + " человек, на " + event.getStage().getName() + " в " + event.getBuilding().getName());
    }
}