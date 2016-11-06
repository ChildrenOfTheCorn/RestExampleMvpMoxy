package com.example.restexample.injection.components;

import com.example.restexample.injection.modules.AuthorizationModule;
import com.example.restexample.injection.scopes.AuthorizationScope;

import dagger.Subcomponent;

/**
 * Created by grishberg on 06.11.16.
 */
@Subcomponent(modules = AuthorizationModule.class)
@AuthorizationScope
public interface AuthorizationComponent {
    // TODO если будет родительским для другого компонента - создать геттер здесь
}
