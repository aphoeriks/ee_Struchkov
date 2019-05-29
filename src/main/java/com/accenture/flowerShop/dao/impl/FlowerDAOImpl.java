package com.accenture.flowerShop.dao.impl;

import com.accenture.flowerShop.dao.FlowerDAO;
import com.accenture.flowerShop.entity.flower.Flower;
import com.accenture.flowerShop.model.FlowerInfo;
import com.accenture.flowerShop.model.PaginationResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class FlowerDAOImpl implements FlowerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Flower findFlower(String name){
        Session session = sessionFactory.getCurrentSession();
        return session.find(Flower.class, name);

    }
    @Override
    public Flower updateFlower(Flower flower){
        sessionFactory.getCurrentSession().update(flower);
        return flower;
    }
    @Override
    public List<Flower> getAllFlowers(){

        Session session =sessionFactory.getCurrentSession();
        String sql = "select f  from "+Flower.class.getName()+" f";

        Query query = session.createQuery(sql);
        return query.getResultList();

    }




}
