package com.ms.Billing_Service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ms.Billing_Service.dto.BillingDto;
import com.ms.Billing_Service.entity.Billing;

@Mapper(componentModel = "spring")
public interface BillingMapper {

    @Mapping(source = "totalAmount", target = "totalAmount")
    @Mapping(source = "projectId", target = "projectId")
    @Mapping(source = "userId", target = "userId")
    BillingDto map(Billing entity);

    List<BillingDto> map(List<Billing> entities);

    @Mapping(source = "totalAmount", target = "totalAmount")
    @Mapping(source = "projectId", target = "projectId")
    @Mapping(source = "userId", target = "userId")
    Billing unMap(BillingDto dto);

    @Mapping(source = "totalAmount", target = "totalAmount")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "projectId", target = "projectId")
    void updateEntityFromDto(@MappingTarget Billing entity, BillingDto dto);
}
