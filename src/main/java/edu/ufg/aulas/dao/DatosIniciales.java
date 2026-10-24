package edu.ufg.aulas.dao;

import edu.ufg.aulas.entidades.EstadoAula;
import edu.ufg.aulas.entidades.Planta;
import edu.ufg.aulas.entidades.TipoAula;
import edu.ufg.aulas.entidades.Usuario;
import edu.ufg.aulas.util.HashUtil;
import edu.ufg.aulas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

// mete los datos de arranque si no hay nada
public final class DatosIniciales {

    public static final String CORREO_PRUEBA = "admin@ufg.edu.sv";
    public static final String CLAVE_PRUEBA = "admin123";

    private DatosIniciales() {
    }

    public static void sembrar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                if (vacia(session, "Usuario")) {
                    session.persist(new Usuario("Admin", "UFG", CORREO_PRUEBA, HashUtil.sha256(CLAVE_PRUEBA), "ADMIN"));
                }
                if (vacia(session, "Planta")) {
                    for (int i = 1; i <= 8; i++) {
                        session.persist(new Planta(i));
                    }
                }
                if (vacia(session, "TipoAula")) {
                    for (String n : new String[]{"Normal", "Centro de computo", "Laboratorio", "Especial"}) {
                        session.persist(new TipoAula(n));
                    }
                }
                if (vacia(session, "EstadoAula")) {
                    for (String n : new String[]{"Asignada", "Asignada temporalmente", "En mantenimiento", "Disponible"}) {
                        session.persist(new EstadoAula(n));
                    }
                }
                tx.commit();
            } catch (RuntimeException e) {
                tx.rollback();
                throw e;
            }
        }
    }

    private static boolean vacia(Session session, String entidad) {
        Long total = session.createQuery("select count(e) from " + entidad + " e", Long.class).getSingleResult();
        return total == 0;
    }
}
