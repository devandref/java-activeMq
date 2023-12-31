package br.com.caelum.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class TesteFilaProdutor {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("financeiro");

        MessageProducer messageProducer = session.createProducer(fila);
        for (int i = 0; i < 1000; i++) {
            Message message = session.createTextMessage("<pedido><id>" + i +"</id><e-book>false</e-book></pedido>");
            message.setBooleanProperty("ebook", Boolean.FALSE);
            messageProducer.send(message);
        }
        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();
    }
}
