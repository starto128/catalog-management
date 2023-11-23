package kr.co.strato.core.domain.config.region.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.strato.core.domain.config.region.entity.ConfigRegion;
import kr.co.strato.core.domain.config.region.entity.QConfigRegion;
import kr.co.strato.core.domain.config.region.model.RegionSearchParam;
import kr.co.strato.core.model.enums.CloudType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomConfigRegionRepositoryImpl implements CustomConfigRegionRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public CustomConfigRegionRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<ConfigRegion> findBySearchParam(Pageable pageable, RegionSearchParam searchParam) {
        QConfigRegion region = QConfigRegion.configRegion;

        BooleanBuilder whereBuilder = new BooleanBuilder();
        if(searchParam != null){
            if(searchParam.getCloudType() != null && searchParam.getCloudType() != CloudType.ALL){
                whereBuilder.and(region.cloudType.eq(searchParam.getCloudType()));
            }
            if(searchParam.getDeletedYn() != null){
                whereBuilder.and(region.deletedYn.eq(searchParam.getDeletedYn()));
            }
        }

        JPAQuery<ConfigRegion> query = jpaQueryFactory
                .select(region)
                .from(region)
                .where(whereBuilder)
                .orderBy(region.cloudType.asc(), region.code.asc());

        if(pageable != null){
            query = query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        QueryResults<ConfigRegion> results = query.fetchResults();

        List<ConfigRegion> content = results.getResults();
        long total = results.getTotal();

        if(pageable == null) {
            return new PageImpl<>(content);
        }

        return new PageImpl<>(content, pageable, total);
    }
}
