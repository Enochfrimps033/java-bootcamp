public class Person {
    // Provided — do not rewrite boilerplate fields
    String name;
    int age;

    // TODO: assign parameters to fields using this.name / this.age
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // TODO: print "<name> is <age> years old"
    public void display() {
        System.out.println(name+ " is " + age + " years old");
    }

    public static void main(String[] args) {
        // TODO: create Person("Aman", 21) and call display()
        Person myObj = new Person("Aman",21);
        myObj.display();
    }
}