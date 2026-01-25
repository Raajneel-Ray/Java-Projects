public class Animal {
    private String name;
    public Animal(String name) {
        this.name = name;
    }
    public String sound() {
        return null;
    }
    public String toString() {
        return name.concat(" says ").concat(sound());
    }

}