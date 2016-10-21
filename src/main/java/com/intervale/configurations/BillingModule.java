package com.intervale.configurations;

import com.google.inject.AbstractModule;
import com.intervale.dao.Core.InterfaceJDBCWrapper;
import com.intervale.dao.JDBCWrapperImpl;
import com.intervale.services.InitBaseService;
import com.intervale.services.InterfaceInitBaseService;

public class BillingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(InterfaceInitBaseService.class).to(InitBaseService.class);
        bind(InterfaceJDBCWrapper.class).to(JDBCWrapperImpl.class);

    }
}