package com.jenschen.security;

import com.jenschen.entity.PermissionEntity;
import com.jenschen.service.PermissionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class MyJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final PermissionService permissionService;

    public MyJwtAuthenticationConverter(PermissionService permissionService){
        this.permissionService = permissionService;
    }
    private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    private String principalClaimName = JwtClaimNames.SUB;

    @Override
    public final AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
        String roleIds = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        List<PermissionEntity> permissionEntityList =
                this.permissionService.getPermissionByRoleIds(Arrays.asList(roleIds.split(",")));

        Set<GrantedAuthority> permissions = new HashSet<>();
        permissionEntityList.forEach(e -> permissions.add(new SimpleGrantedAuthority(e.getPermission())));

        String principalClaimValue = jwt.getClaimAsString(this.principalClaimName);
        return new JwtAuthenticationToken(jwt, permissions, principalClaimValue);
    }

    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        return this.jwtGrantedAuthoritiesConverter.convert(jwt);
    }

    public void setJwtGrantedAuthoritiesConverter(
            Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter) {
        Assert.notNull(jwtGrantedAuthoritiesConverter, "jwtGrantedAuthoritiesConverter cannot be null");
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
    }

    public void setPrincipalClaimName(String principalClaimName) {
        Assert.hasText(principalClaimName, "principalClaimName cannot be empty");
        this.principalClaimName = principalClaimName;
    }

}
