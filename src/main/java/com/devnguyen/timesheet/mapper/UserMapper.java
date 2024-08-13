package com.devnguyen.timesheet.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import com.devnguyen.timesheet.dto.request.AccountRequest;
import com.devnguyen.timesheet.model.User;

@Mapper
@Component
public interface UserMapper {

     UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(AccountRequest accountRequest);
    
    AccountRequest toDto(User user);
    
}
