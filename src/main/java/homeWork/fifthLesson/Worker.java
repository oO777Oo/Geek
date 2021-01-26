package homeWork.fifthLesson;

public class Worker {
    private String name;
    private String position;
    private String mail;
    private String phoneNumber;
    private double salary;
    private int age;

    public Worker() {
    }

    public Worker(String name, String position, String mail, String phoneNumber, double salary, int age) {
        this.name = name;
        this.position = position;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void printInformation() {
        System.out.println("Сотрудник по имени: " + this.name + " с должностю: " + this.position);
        System.out.println("Mail: " + this.mail + " телефон: " + this.phoneNumber + " зарплата: " + this.salary + " возраст: " + this.age);
    }

    public int getAge() {
        return age;
    }
}
