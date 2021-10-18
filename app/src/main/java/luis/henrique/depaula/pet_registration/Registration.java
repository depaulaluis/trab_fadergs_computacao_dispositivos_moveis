package luis.henrique.depaula.pet_registration;

public class Registration {

    public int id;

    public String petName, yearsPet, specie;

    public Registration() {
    }

    public Registration(String petName, String yearsPet, String specie) {
        this.petName = petName;
        this.yearsPet = yearsPet;
        this.specie = specie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getYearsPet() {
        return yearsPet;
    }

    public void setYearsPet(String yearsPet) {
        this.yearsPet = yearsPet;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }
}
