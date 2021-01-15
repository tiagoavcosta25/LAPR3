package lapr.project.ui;

import lapr.project.utils.EmailSender;

class Main {

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        System.out.println("Hello");
        EmailSender.emailSender("farmacyservice.g21@gmail.com", "Subject do email", "Olá!\nO meu nome é Tiago e bem vindo à forex!");
    }
}