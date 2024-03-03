package dev.tadeupinheiro.aplicativo;

import dev.tadeupinheiro.dominio.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Programa {

    public static void main(String[] args) {

        Pessoa p1 = new Pessoa(null, "Carlos da Silva", "carlos@gmail.com");
        Pessoa p2 = new Pessoa(null, "Joaquim Torres", "joaquim@gmail.com");
        Pessoa p3 = new Pessoa(null, "Ana Maria", "ana@gmail.com");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        /*
        *Essa instância serve para criar o entitymanager e usar aquelas configuralçies do persistence.xml
        * o nome "exemplo-java" é o nome que foi dado ao persistence unit dentro do arquivo.
        */
        EntityManager em = emf.createEntityManager(); //Criada com a conexão com banco de dados

        //Para uma operação que não é uma simples leitura, precisa-se iniciar uma transação
        em.getTransaction().begin();

        /*
        em.persist(p1); //persist() é o método que salva no banco de dados
        em.persist(p2);
        em.persist(p3);
        */

        /*
        //Método find busca a classe dentro da tabela e já retorna para o tipo do objeto
        Pessoa p = em.find(Pessoa.class, 2);
        */

        /*
        //Não remove este objeto pois ele está destacado e NÃO monitorado
        Pessoa p = new Pessoa(2, null, null);
        em.remove(p);
         */

        //Forma correta de remover
        Pessoa p = em.find(Pessoa.class, 2);
        em.remove(p);
        
        em.getTransaction().commit(); //Confirmar as alterações que foram feitas

        System.out.println("Pronto!");

        em.close(); //Fechando o entity manager
        emf.close(); //Fechando o entity manager factory
    }
}
