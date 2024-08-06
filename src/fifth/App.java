package fifth;

import fifth.da.ProductDa;
import fifth.entity.Product;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        ProductDa productDa = new ProductDa();
        Product product;
        String name;
        int sum=0;
        int option = 0;
        do {
            try {
                System.out.println("1)Add product");
                System.out.println("2)Edit Product");
                System.out.println("3)Remove Product");
                System.out.println("4)Show All product (invoice list)");
                System.out.println("5)Total");
                System.out.println("6)Print Invoice");
                System.out.println("0)Exit");

                System.out.print("Enter Choice :");
                option = Integer.parseInt(scanner.nextLine());

                System.out.println("-----------------------------------------");

                switch (option) {
                    case 1:

                        product = new Product();
                        System.out.print("Enter name : ");
                        product.setName(scanner.nextLine());

                        System.out.print("Enter price : ");
                        product.setPrice(Integer.parseInt(scanner.nextLine()));

                        System.out.print("Enter count : ");
                        product.setCount(Integer.parseInt(scanner.nextLine()));

                        if (productDa.findByName(product.getName()) == null) {
                            productDa.save(product);
                            System.out.println("Product Saved");
                        } else {
                            System.err.println("product name is duplicated, change It !!!");
                        }

                        break;

                    case 2:
                        product = new Product();
                        System.out.print("Enter Id : ");
                        product.setId(Integer.parseInt(scanner.nextLine()));

                        System.out.print("Enter name : ");
                        product.setName(scanner.nextLine());

                        System.out.print("Enter price : ");
                        product.setPrice(Integer.parseInt(scanner.nextLine()));

                        System.out.print("Enter count : ");
                        product.setCount(Integer.parseInt(scanner.nextLine()));

                        productDa.edit(product);
                        System.out.println("product Edited");
                        break;

                    case 3:
                        System.out.print("Enter Id : ");
                        int id = Integer.parseInt(scanner.nextLine());

                        productDa.remove(id);
                        System.out.println("Product Removed");
                        break;

                    case 4:
                        System.out.println("Product List");
                        for (Product u : productDa.findAll()) {
                            System.out.printf("User : %15s  Pass : ******** %n", u.getName());
                        }
                        break;

                    case 5:
                        int totalSum = 0;
                        for (Product p : productDa.findAll()) {
                            totalSum +=p.getPrice() * p.getCount();
                        }
                        System.out.printf("Total Price: %d%n", totalSum);

                    break;

                    case 6:

                        class print {
                            public void printInvoice(List<Product> products) {
                                System.out.printf("%-20s %-10s %-10s%n", "name", "count", "price");
                                System.out.println("-------------------------------------");

                                int total = 0;
                                for (Product product : products) {
                                    System.out.printf("%-20s %-10d %-10d%n", product.getName(), product.getCount(), product.getPrice());
                                    total += product.getPrice() * product.getCount();
                                }

                                System.out.println("-------------------------------------");
                                System.out.printf("%-20s %-10s %-10d%n", "total price", "", total);
                                System.out.println("-------------------------------------");
                            }
                        }


                        break;

                    case 0:
                        System.out.println("Bye");
                        break;
                    default:
                        System.err.println("Invalid Choice");

                }
                System.out.println("-----------------------------------------");
            } catch (Exception e) {
                System.err.println("Error : " + e.getMessage() + "Try again Later !!!");
            }
        } while (option != 0);
    }
}

