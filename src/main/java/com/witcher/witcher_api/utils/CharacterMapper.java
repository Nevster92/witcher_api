package com.witcher.witcher_api.utils;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.witcher.witcher_api.model.pojo.Character;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CharacterMapper {

    @Mapping(target = "r_arm", ignore = true)
    @Mapping(target = "l_arm", ignore = true)
    @Mapping(target = "head", ignore = true)
    @Mapping(target = "torso", ignore = true)
    @Mapping(target = "leg", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateCharacterFromDto(Character newCharacter, @MappingTarget Character character );
}
