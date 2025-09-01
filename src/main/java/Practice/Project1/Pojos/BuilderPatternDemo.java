package Practice.Project1.Pojos;

class User {
    private final String name;
    private final String email;

    private  int age;
    private  String phoneNumber;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.phoneNumber = builder.phoneNumber;
    }

    public static class Builder {

        private final String name;
        private final String email;

        private int age;
        private String phoneNumber;

         public Builder(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

         User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email +
                "', age=" + age + ", phoneNumber='" + phoneNumber + "'}";
    }
}

public class BuilderPatternDemo {
    public static void main(String[] args) {
        User user1 = new User.Builder("Alice", "abc@example.com")
                .age(25)
                .phoneNumber("123-456-7890")
                .build();

        User user2 = new User.Builder("Bob","wjhebf@gmail.com").build();

        System.out.println(user1);
        System.out.println(user2);
    }
}
