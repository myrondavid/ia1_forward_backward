package Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DatabaseFato {
    private Configuration cfg = new Configuration()
            .addAnnotatedClass(FatoDb.class)
            .configure();
    private SessionFactory sessionFactory;
    private Session session;


    public FatoDb persist(FatoDb f){
        try{
            this.sessionFactory = cfg.buildSessionFactory();
            this.session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(f);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }

    public boolean delete(int id){
        try{
            this.sessionFactory = cfg.buildSessionFactory();
            this.session = sessionFactory.openSession();
            session.beginTransaction();
            FatoDb s = session.find(FatoDb.class, id);
            session.remove(s);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public FatoDb getById(int id){
        try{
            this.sessionFactory = cfg.buildSessionFactory();
            this.session = sessionFactory.openSession();
            session.beginTransaction();
            FatoDb s = session.find(FatoDb.class, id);
            session.getTransaction().commit();
            session.close();
            return s;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<FatoDb> getAll(){
        try{
            this.sessionFactory = cfg.buildSessionFactory();
            this.session = sessionFactory.openSession();
            session.beginTransaction();
            List<FatoDb> fatosList = session.createCriteria(FatoDb.class).list();
            session.getTransaction().commit();
            session.close();
            return fatosList;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void clear(){
        try{
            this.sessionFactory = cfg.buildSessionFactory();
            this.session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM fato").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
