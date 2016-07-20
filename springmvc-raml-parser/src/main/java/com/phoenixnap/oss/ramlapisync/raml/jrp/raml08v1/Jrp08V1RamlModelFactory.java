package com.phoenixnap.oss.ramlapisync.raml.jrp.raml08v1;

import com.phoenixnap.oss.ramlapisync.data.RamlFormParameter;
import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlActionType;
import com.phoenixnap.oss.ramlapisync.raml.RamlDocumentationItem;
import com.phoenixnap.oss.ramlapisync.raml.RamlHeader;
import com.phoenixnap.oss.ramlapisync.raml.RamlMimeType;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelEmitter;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelFactory;
import com.phoenixnap.oss.ramlapisync.raml.RamlParamType;
import com.phoenixnap.oss.ramlapisync.raml.RamlQueryParameter;
import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlResponse;
import com.phoenixnap.oss.ramlapisync.raml.RamlRoot;
import com.phoenixnap.oss.ramlapisync.raml.RamlSecurityReference;
import com.phoenixnap.oss.ramlapisync.raml.RamlUriParameter;
import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.DocumentationItem;
import org.raml.model.MimeType;
import org.raml.model.ParamType;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.Response;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.FormParameter;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;
import org.raml.parser.visitor.RamlDocumentBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author armin.weisser
 */
public class Jrp08V1RamlModelFactory implements RamlModelFactory {

    @Override
    public RamlModelEmitter createRamlModelEmitter() {
        return new Jrp08V1RamlModelEmitter();
    }

    @Override
    public RamlRoot buildRamlRoot(String ramlFileUrl) {
        return createRamlRoot(new RamlDocumentBuilder().build(ramlFileUrl));
    }

    @Override
    public RamlRoot createRamlRoot() {
        return createRamlRoot(new Raml());
    }

    @Override
    public RamlRoot createRamlRoot(String ramlFileUrl) {
        Raml raml = new RamlDocumentBuilder().build(ramlFileUrl);
        return createRamlRoot(raml);
    }

    RamlRoot createRamlRoot(Raml raml) {
        if (raml == null) {
            return null;
        }
        return new Jrp08V1RamlRoot(raml);
    }

    @Override
    public RamlResource createRamlResource() {
        return createRamlResource(new Resource());
    }

    @Override
    public RamlResource createRamlResource(Object resource) {
        if (resource == null) {
            return null;
        }
        return new Jrp08V1RamlResource((Resource) resource);
    }

    Resource extractResource(RamlResource ramlResource) {
        if (ramlResource == null) return null;
        return ((Jrp08V1RamlResource) ramlResource).getResource();
    }

    @Override
    public RamlAction createRamlAction(Object action) {
        if (action == null) {
            return null;
        }
        return new Jrp08V1RamlAction((Action) action);
    }

    @Override
    public RamlAction createRamlAction() {
        return createRamlAction(new Action());
    }

    Action extractAction(RamlAction ramlAction) {
        return ((Jrp08V1RamlAction) ramlAction).getAction();
    }

    @Override
    public RamlDocumentationItem createRamlDocumentationItem() {
        return createRamlDocumentationItem(new DocumentationItem());
    }

    @Override
    public RamlDocumentationItem createRamlDocumentationItem(Object documentationItem) {
        return new Jrp08V1RamlDocumentationItem((DocumentationItem)documentationItem);
    }

    List<DocumentationItem> extractDocumentationItems(List<RamlDocumentationItem> ramlRocumentationItems) {
        if (ramlRocumentationItems == null) {
            return null;
        }
        return ramlRocumentationItems.stream()
                .map(ramlDocumentationItem -> extractDocumentationItem(ramlDocumentationItem))
                .collect(Collectors.toList());
    }

    private DocumentationItem extractDocumentationItem(RamlDocumentationItem ramlDocumentationItem) {
        return ((Jrp08V1RamlDocumentationItem) ramlDocumentationItem).getDocumentationItem();
    }

    @Override
    public RamlActionType createRamlActionType(Object type) {
        return RamlActionType.valueOf(((ActionType)type).name());
    }

    ActionType extractActionType(RamlActionType ramlActionType) {
        return ActionType.valueOf(ramlActionType.name());
    }

    @Override
    public RamlResponse createRamlResponse() {
        return createRamlResponse(new Response());
    }

    public RamlResponse createRamlResponse(Object response) {
        if(response == null) {
            return null;
        }
        return new Jrp08V1RamlResponse((Response)response);
    }


    Response extractResponse(RamlResponse ramlResponse) {
        return ((Jrp08V1RamlResponse)ramlResponse).getResponse();
    }

    @Override
    public RamlMimeType createRamlMimeType() {
        return createRamlMimeType(new MimeType());
    }

    @Override
    public RamlMimeType createRamlMimeTypeWithMime(String mime) {
        return createRamlMimeType(new MimeType(mime));
    }

    @Override
    public RamlMimeType createRamlMimeType(Object mimeType) {
        return new Jrp08V1RamlMimeType((MimeType)mimeType);
    }

    @Override
    public RamlHeader createRamlHeader(Object header) {
        return new Jrp08V1RamlHeader((Header)header);
    }

    @Override
    public RamlUriParameter createRamlUriParameter(Object uriParameter) {
        return new Jrp08V1RamlUriParameter((UriParameter)uriParameter);
    }

    Map<String, MimeType> extractBody(Map<String, RamlMimeType> ramlBody) {
        Map<String, MimeType> body = new LinkedHashMap<>(ramlBody.size());
        for(String key: ramlBody.keySet()) {
            body.put(key, extractMimeType(ramlBody.get(key)));
        }
        return body;
    }

    MimeType extractMimeType(RamlMimeType ramlMimeType) {
        return ((Jrp08V1RamlMimeType)ramlMimeType).getMimeType();
    }

    UriParameter extractUriParameter(RamlUriParameter ramlUriParameter) {
        return ((Jrp08V1RamlUriParameter)ramlUriParameter).getUriParameter();
    }

    @Override
    public RamlUriParameter createRamlUriParameterWithName(String name) {
        return new Jrp08V1RamlUriParameter(new UriParameter(name));
    }

    @Override
    public RamlQueryParameter createRamlQueryParameter() {
        return createRamlQueryParameter(new QueryParameter());
    }

    @Override
    public RamlQueryParameter createRamlQueryParameter(Object queryParameter) {
        return new Jrp08V1RamlQueryParameter((QueryParameter)queryParameter);
    }

    QueryParameter extractQueryParameter(RamlQueryParameter ramlQueryParameter) {
        return ((Jrp08V1RamlQueryParameter)ramlQueryParameter).getQueryParameter();
    }

    Map<String, List<FormParameter>> extractFormParameters(Map<String, List<RamlFormParameter>> ramlFormParameters) {
        Map<String, List<FormParameter>> formParameters = new LinkedHashMap<>(ramlFormParameters.size());
        for(String key: ramlFormParameters.keySet()) {
            formParameters.put(key, extractFormParameters(ramlFormParameters.get(key)));
        }
        return formParameters;
    }

    List<FormParameter> extractFormParameters(List<RamlFormParameter> ramlFormParameters) {
        return ramlFormParameters.stream().map(this::extractFormParameter).collect(Collectors.toList());
    }

    FormParameter extractFormParameter(RamlFormParameter ramlFormParameter) {
        return ((Jrp08V1RamlFormParameter)ramlFormParameter).getFormParameter();
    }

    @Override
    public RamlFormParameter createRamlFormParameter() {
        return createRamlFormParameter(new FormParameter());
    }

    @Override
    public List<RamlFormParameter> createRamlFormParameters(List<? extends Object> formParameters) {
        return formParameters.stream().map(this::createRamlFormParameter).collect(Collectors.toList());
    }

    @Override
    public RamlFormParameter createRamlFormParameter(Object formParameter) {
        return new Jrp08V1RamlFormParameter((FormParameter)formParameter);
    }

    @Override
    public List<RamlSecurityReference> createRamlSecurityReferences(List<? extends Object> securityReferences) {
        return securityReferences.stream().map(this::createRamlSecurityReference).collect(Collectors.toList());
    }

    @Override
    public RamlSecurityReference createRamlSecurityReference(Object securityReference) {
        return new Jrp08V1RamlSecurityReference((SecurityReference)securityReference);
    }

    @Override
    public RamlParamType createRamlParamType(Object paramType) {
        return RamlParamType.valueOf(((ParamType)paramType).name());
    }

    ParamType extractRamlParam(RamlParamType ramlParamType) {
        return ParamType.valueOf(ramlParamType.name());
    }
}
