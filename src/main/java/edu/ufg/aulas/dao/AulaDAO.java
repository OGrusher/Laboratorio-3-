package edu.ufg.aulas.dao;

import edu.ufg.aulas.entidades.Aula;
import edu.ufg.aulas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AulaDAO {

    // revisa si el aula ya existe
    public boolean existe(String nombre, String edificio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long total = session.createQuery(
                            "select count(a) from Aula a "
                                    + "where lower(a.nombreAula) = lower(:nombre) "
                                    + "and lower(a.edificio) = lower(:edificio)", Long.class)
                    .setParameter("nombre", nombre)
                    .setParameter("edificio", edificio)
                    .getSingleResult();
            return total > 0;
        }
    }

    public void guardar(Aula aula) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.persist(aula);
                tx.commit();
            } catch (RuntimeException e) {
                tx.rollback();
                throw e;
            }
        }
    }
}
