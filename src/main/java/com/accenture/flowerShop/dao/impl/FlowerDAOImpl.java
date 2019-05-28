package com.accenture.flowerShop.dao.impl;

import com.accenture.flowerShop.dao.FlowerDAO;
import com.accenture.flowerShop.entity.flower.Flower;
import com.accenture.flowerShop.entity.flower.Stock;
import com.accenture.flowerShop.model.FlowerInfo;
import com.accenture.flowerShop.model.PaginationResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
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
    public List<Flower> getAllFlowers(){

        Session session =sessionFactory.getCurrentSession();
        String sql = "select f  from "+Flower.class.getName()+" f";

        Query query = session.createQuery(sql);
        return query.getResultList();

    }
    @Override
    public List<Stock> getAllStocks(){
        Session session =sessionFactory.getCurrentSession();
        String sql = "select s  from "+Stock.class.getName()+" s";

        Query query = session.createQuery(sql);
        return query.getResultList();
    }
    @Transactional
    @Override
    public boolean updateStocks(List<Stock> stocks) throws Exception{
        Session session =sessionFactory.getCurrentSession();
        for(Stock stock : stocks){
            session.update(stock);
        }
        return true;
    }


    @Override
    public PaginationResult<FlowerInfo> queryFlowers(
            int page,
            int maxResult,
            int maxNavigationPage,
            String likeName,
            double priceMin,
            double priceMax
    ){
//TODO criteria api
        Session session =sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<FlowerInfo> query1 = builder.createQuery(FlowerInfo.class);
        Root<Flower> flowers = query1.from(Flower.class);
        query1.select(builder.construct(FlowerInfo.class, flowers.get("name"), flowers.get("price"), flowers.get("stock").get("quantity")));
        List<Predicate> criteria = new ArrayList<>();

        if (likeName != null && likeName.length() > 0) {
            criteria.add(builder.like(builder.lower(flowers.get("name")), "%"+likeName.toLowerCase()+"%"));
        }
        if (priceMin > 0 ){
            criteria.add(builder.greaterThanOrEqualTo(flowers.get("price"), priceMin));
        }
        if (priceMax > 0 && priceMax > priceMin){
            criteria.add(builder.lessThanOrEqualTo(flowers.get("price"), priceMax));
        }
        for(Predicate p: criteria){
            query1.where(builder.and(p));
        }
        query1.orderBy(builder.desc(flowers.get("name")));
        Query query = session.createQuery(query1);


        return new PaginationResult<>(query, page, maxResult, maxNavigationPage);
    }
}
