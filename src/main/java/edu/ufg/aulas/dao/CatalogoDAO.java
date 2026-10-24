package edu.ufg.aulas.dao;

import edu.ufg.aulas.entidades.EstadoAula;
import edu.ufg.aulas.entidades.TipoAula;
import edu.ufg.aulas.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

// llena los combobox del home
public class CatalogoDAO {

    public List<Integer> listarPlantas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select p.numero from Planta p order by p.numero", Integer.class)
                    .getResultList();
        }
    }

    public List<TipoAula> listarTipos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from TipoAula t order by t.id", TipoAula.class).getResultList();
        }
    }

    public List<EstadoAula> listarEstados() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from EstadoAula e order by e.id", EstadoAula.class).getResultList();
        }
    }
}
