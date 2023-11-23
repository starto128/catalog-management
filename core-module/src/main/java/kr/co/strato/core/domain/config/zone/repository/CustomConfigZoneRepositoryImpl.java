package kr.co.strato.core.domain.config.zone.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.strato.core.domain.config.zone.entity.ConfigZone;
import kr.co.strato.core.domain.config.zone.entity.QConfigZone;
import kr.co.strato.core.domain.config.zone.model.ZoneSearchParam;
import kr.co.strato.core.model.enums.CloudType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomConfigZoneRepositoryImpl implements CustomConfigZoneRepository{
    private final JPAQueryFactory jpaQueryFactory;

    public CustomConfigZoneRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<ConfigZone> findBySearchParam(Pageable pageable, ZoneSearchParam searchParam) {
        QConfigZone zone = QConfigZone.configZone;

        BooleanBuilder whereBuilder = new BooleanBuilder();
        if(searchParam != null){
            if(searchParam.getCloudType() != null && searchParam.getCloudType() != CloudType.ALL){
                whereBuilder.and(zone.cloudType.eq(searchParam.getCloudType()));
            }
            if(searchParam.getRegionCode() != null){
                whereBuilder.and(zone.regionCode.eq(searchParam.getRegionCode()));
            }
            if(searchParam.getDeletedYn() != null){
                whereBuilder.and(zone.deletedYn.eq(searchParam.getDeletedYn()));
            }
        }

        JPAQuery<ConfigZone> query = jpaQueryFactory
                .select(zone)
                .from(zone)
                .where(whereBuilder);

        if(pageable != null){
            query = query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        QueryResults<ConfigZone> results = query.fetchResults();

        List<ConfigZone> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
