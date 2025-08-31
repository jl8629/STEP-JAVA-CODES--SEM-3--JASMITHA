import java.util.Scanner;

class Product {
    String productId, productName, category;
    double price;
    int stockQuantity;
    static int totalProducts=0;
    static String[] categories=new String[10];
    private static int idc=1;

    public Product(String name,String category,double price,int stock){
        this.productId="P"+idc++;
        this.productName=name;
        this.category=category;
        this.price=price;
        this.stockQuantity=stock;
        totalProducts++;
    }

    public static Product findProductById(Product[] products,String productId){
        for(Product p:products){
            if(p!=null && p.productId.equals(productId)) return p;
        }
        return null;
    }

    public static void getProductsByCategory(Product[] products,String category){
        for(Product p:products){
            if(p!=null && p.category.equalsIgnoreCase(category)){
                System.out.println(p.productId+" "+p.productName+" Rs."+p.price+" Stock:"+p.stockQuantity);
            }
        }
    }

    public void display(){
        System.out.println(productId+" "+productName+" ("+category+") Rs."+price+" Stock:"+stockQuantity);
    }
}

class ShoppingCart {
    String cartId,customerName;
    Product[] products=new Product[20];
    int[] quantities=new int[20];
    int count=0;
    double cartTotal=0;
    private static int idc=1;

    public ShoppingCart(String name){
        this.customerName=name;
        this.cartId="C"+idc++;
    }

    public void addProduct(Product product,int qty){
        if(product!=null && product.stockQuantity>=qty){
            products[count]=product;
            quantities[count]=qty;
            product.stockQuantity-=qty;
            count++;
            calculateTotal();
            System.out.println(qty+" "+product.productName+" added to cart.");
        }else{
            System.out.println("Product unavailable or insufficient stock.");
        }
    }

    public void removeProduct(String productId){
        for(int i=0;i<count;i++){
            if(products[i].productId.equals(productId)){
                products[i].stockQuantity+=quantities[i];
                for(int j=i;j<count-1;j++){
                    products[j]=products[j+1];
                    quantities[j]=quantities[j+1];
                }
                products[count-1]=null; quantities[count-1]=0;
                count--;
                calculateTotal();
                System.out.println("Product "+productId+" removed.");
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }

    public void calculateTotal(){
        cartTotal=0;
        for(int i=0;i<count;i++){
            cartTotal+=products[i].price*quantities[i];
        }
    }

    public void displayCart(){
        System.out.println("\nCart for "+customerName+" ("+cartId+")");
        for(int i=0;i<count;i++){
            System.out.println(products[i].productName+" x "+quantities[i]+" = Rs."+(products[i].price*quantities[i]));
        }
        System.out.println("Cart Total: Rs."+cartTotal);
    }

    public void checkout(){
        if(count==0){
            System.out.println("Cart is empty!");
            return;
        }
        displayCart();
        System.out.println("Checkout complete. Thank you for shopping!");
        for(int i=0;i<count;i++){
            products[i]=null; quantities[i]=0;
        }
        count=0; cartTotal=0;
    }
}

public class ShoppingCartSystem {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        Product[] catalog=new Product[10];
        catalog[0]=new Product("Laptop","Electronics",50000,5);
        catalog[1]=new Product("Phone","Electronics",25000,10);
        catalog[2]=new Product("Headphones","Electronics",2000,15);
        catalog[3]=new Product("Shirt","Clothing",1200,20);
        catalog[4]=new Product("Jeans","Clothing",2500,10);
        catalog[5]=new Product("Shoes","Clothing",3000,8);
        catalog[6]=new Product("Rice","Grocery",1200,30);
        catalog[7]=new Product("Oil","Grocery",1800,25);
        catalog[8]=new Product("Milk","Grocery",60,50);
        catalog[9]=new Product("Book","Stationery",500,12);

        System.out.print("Enter customer name: ");
        String cname=sc.next();
        ShoppingCart cart=new ShoppingCart(cname);

        int choice;
        do{
            System.out.println("\n--- Shopping Menu ---");
            System.out.println("1. View All Products");
            System.out.println("2. Search Product by ID");
            System.out.println("3. View Products by Category");
            System.out.println("4. Add Product to Cart");
            System.out.println("5. Remove Product from Cart");
            System.out.println("6. View Cart");
            System.out.println("7. Checkout");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice=sc.nextInt();

            switch(choice){
                case 1:
                    for(Product p:catalog) p.display();
                    break;
                case 2:
                    System.out.print("Enter Product ID: ");
                    String pid=sc.next();
                    Product found=Product.findProductById(catalog,pid);
                    if(found!=null) found.display(); else System.out.println("Not found.");
                    break;
                case 3:
                    System.out.print("Enter Category: ");
                    String cat=sc.next();
                    Product.getProductsByCategory(catalog,cat);
                    break;
                case 4:
                    System.out.print("Enter Product ID: ");
                    String pidAdd=sc.next();
                    Product pAdd=Product.findProductById(catalog,pidAdd);
                    System.out.print("Enter Quantity: ");
                    int qty=sc.nextInt();
                    cart.addProduct(pAdd,qty);
                    break;
                case 5:
                    System.out.print("Enter Product ID to remove: ");
                    String pidRem=sc.next();
                    cart.removeProduct(pidRem);
                    break;
                case 6:
                    cart.displayCart();
                    break;
                case 7:
                    cart.checkout();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while(choice!=8);

        sc.close();
    }
}
