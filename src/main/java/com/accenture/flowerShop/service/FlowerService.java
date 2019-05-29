package com.accenture.flowerShop.service;

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
import java.util.ArrayList;
import java.util.List;
@Service
public class FlowerService {
    @Autowired
    FlowerDAO flowerDAO;
    @Autowired
    private SessionFactory sessionFactory;

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
        query1.select(builder.construct(FlowerInfo.class, flowers.get("name"), flowers.get("price"), flowers.get("quantity")));
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
