package jpabook.jpashop;

import antlr.StringUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);
            Root<Member> m = query.from(Member.class);
            CriteriaQuery<Member> cq = query.select(m);

            String username = "han";
            if(username != null) {
                cq.where(cb.equal(m.get("name"), username));
            }

            List<Member> resultList = em.createQuery(cq)
                .getResultList();

            String sql = "select member_id, city, street from member";
            List result = em.createNativeQuery(sql).getResultList();

//            String name = proxyMember.getName();
//            System.out.println("name = " + name);
//            Member findMember = em.find(Member.class, member.getId());
//            boolean isSame = findMember == proxyMember;
//
//            System.out.println("isSame = " + isSame);
//            System.out.println("findMember.getClass() : " + findMember.getClass());
//            System.out.println("Member.getClass() : " + proxyMember.getClass());

            TypedQuery<Member> typedQuery = em.createQuery("select m from Member as m where m.name = :name", Member.class);
            typedQuery.setParameter("name", "han");



            Query query = em.createQuery("select m.name from Member as m");

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