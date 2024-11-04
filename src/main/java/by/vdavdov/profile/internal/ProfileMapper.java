package by.vdavdov.profile.internal;

import by.vdavdov.profile.ContactTo;
import by.vdavdov.profile.ProfileTo;
import by.vdavdov.profile.internal.model.Contact;
import by.vdavdov.profile.internal.model.Profile;
import by.vdavdov.profile.internal.web.ProfilePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "mailNotifications", expression = "java(ProfileUtil.maskToNotifications(entity.getMailNotifications()))")
    ProfileTo toTo(Profile entity);

    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "mailNotifications", expression = "java(ProfileUtil.notificationsToMask(to.getMailNotifications()))")
    Profile updateFromTo(@MappingTarget Profile entity, ProfileTo to);

    Contact toContact(ContactTo contact);

    @Mapping(target = "contacts", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
    ProfileTo fromPostToTo(ProfilePostRequest profilePostRequest);
}
