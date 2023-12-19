package br.com.caelum.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteProdutorTopico {

    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination topico = (Destination) context.lookup("loja");

        MessageProducer producer = session.createProducer(topico);

        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("<pedido><id>" + i + "</id></pedido>");
            producer.send(textMessage);
        }

        session.close();
        connection.close();
        context.close();


    }
}
