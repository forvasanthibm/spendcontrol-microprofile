package com.ibm.spendcontrol.microprofile.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@ApplicationPath("/")
@OpenAPIDefinition(info = @Info(
        title = "SpendControl Application On MicroProfile", 
        version = "1.0.0", 
        contact = @Contact(
                name = "Vasantha Kumar N", 
                email = "vasanthakumar@in.ibm.com",
                url = "http://www.ibm.com")
        )
)
public class RestApplication extends Application {
}
