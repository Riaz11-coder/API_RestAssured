package POJO;

import com.github.javafaker.Faker;

public class Spartan {



        private String name;
        private String gender;
        private long phone;

    public static Spartan createRandomSpartanObject() {
        Faker faker = new Faker();
        String name   = faker.name().firstName();
        String gender = faker.demographic().sex();
        // always getting long number outside range of int to avoid errors
        long phone    = faker.number().numberBetween(5000000000L,9999999999L);
        Spartan randomSpartanObject = new Spartan(name,gender,phone);
        System.out.println("Created Random Spartan Object : " + randomSpartanObject);
        return randomSpartanObject ;
    }



    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public long getPhone() {
        return phone;
    }


    @Override
    public String toString() {
        return "Spartan{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }

    public Spartan(String name, String gender, long phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }


}
