package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("Han");
            
            em.persist(member);

            em.flush();
            em.clear();

            Member proxyMember = em.getReference(Member.class, member.getId());

            em.flush();
            em.clear();

//            String name = proxyMember.getName();
//            System.out.println("name = " + name);
//            Member findMember = em.find(Member.class, member.getId());
//            boolean isSame = findMember == proxyMember;
//
//            System.out.println("isSame = " + isSame);
//            System.out.println("findMember.getClass() : " + findMember.getClass());
//            System.out.println("Member.getClass() : " + proxyMember.getClass());
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();

        System.out.println("Hello world!");
    }
}