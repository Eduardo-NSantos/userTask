package com.eduardo.userTask.security;

import com.eduardo.userTask.enums.UserRole;
import com.eduardo.userTask.infrastructure.entities.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;

    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getRole() == UserRole.ADMIN){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    @NonNull
    public String getUsername() {
        return user.getEmail();
    }

    public Integer getId(){
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
