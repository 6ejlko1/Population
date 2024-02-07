import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );

            // Поиск несовершеннолетних
            long ages = persons.stream()
                    .filter(person -> person.getAge() < 18)
                    .count();

            // Поиск фамилий призывников
            List<String> conscript = persons.stream()
                    .filter(person -> person.getAge() > 18)
                    .filter(person -> person.getAge() < 27)
                    .filter(person -> person.getSex() == Sex.MAN)
                    .map(Person::getFamily)
                    .collect(Collectors.toList());

            // Поиск работоспособных людей
            Collection<Person> efficient = persons.stream()
                    .filter(person -> person.getEducation() == Education.HIGHER)
                    .filter(person -> person.getAge() > 18 && person.getAge() < 60 && person.getSex() == Sex.WOMAN)
                    .filter(person -> person.getAge() > 18 && person.getAge() < 65 && person.getSex() == Sex.MAN)
                    .sorted(Comparator.comparing(Person::getFamily))
                    .collect(Collectors.toList());
        }
    }
}
