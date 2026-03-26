package com.example.lab1_tos;

public class Main {

    public static void main(String[] args) {
        double x = Math.PI / 3;
        double result = CosineFunction.cosSeries(x);
        System.out.println("cos(" + x + ") = " + result);

        int[] array = {5, 2, 9, 1, 5, 6};
        System.out.println("До сортировки: " + java.util.Arrays.toString(array));
        ShellSort.shellSort(array);
        System.out.println("После сортировки: " + java.util.Arrays.toString(array));

        DomainModel.Building building = new DomainModel.Building(
                "Здание с помостом",
                "Второй этаж",
                150
        );

        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        speaker.setInfo("Сегодня буду говорить о важности единства.");

        DomainModel.Person arthur = new DomainModel.Person("Артур");
        DomainModel.Person alex = new DomainModel.Person("Алекс");

        DomainModel.Crowd crowd = new DomainModel.Crowd(building);
        crowd.addPerson(speaker);
        crowd.addPerson(arthur);
        crowd.addPerson(alex);

        String stage = "Помост";

        DomainModel.Event event = new DomainModel.Event(speaker, crowd, stage, building);

        System.out.println("Событие произошло в " + event.getBuilding().getName());
        System.out.println("Количество людей в толпе: " + event.getCrowd().getSize());
        System.out.println("Максимальная вместимость: " + event.getCrowd().getMaxCapacity());

        event.startSpeech("inspirational");

        System.out.println(speaker.getName() + ": " + speaker.getMood());
        System.out.println(arthur.getName() + ": " + arthur.getMood());
        System.out.println(alex.getName() + ": " + alex.getMood());

        crowd.interact();

        System.out.println("После взаимодействия:");
        System.out.println(speaker.getName() + ": " + speaker.getMood());
        System.out.println(arthur.getName() + ": " + arthur.getMood());
        System.out.println(alex.getName() + ": " + alex.getMood());
    }
}