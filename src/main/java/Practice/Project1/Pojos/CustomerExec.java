package Practice.Project1.Pojos;

public class CustomerExec {
    public static void main(String[] args) {
        Customer customer=new Customer("abc","abc@gmail.com",22,"3428789");

        System.out.println(customer.getName());
        System.out.println(customer.getAge());
        System.out.println(customer.getEmail());
        System.out.println(customer.getPhoneNumber());

    }
}
