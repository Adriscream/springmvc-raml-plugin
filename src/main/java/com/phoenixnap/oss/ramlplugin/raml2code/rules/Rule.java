/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.phoenixnap.oss.ramlplugin.raml2code.rules;

/**
 * Represents a generic code generation rule (e.g. production of JCodeModel from RAML schema).
 * The Rule can be executed or 'applied' to perform the code generation step.
 *
 * @param <T>
 *            The type of the source code item on which this rule can operate. E.g. JClass.
 * @param <R>
 *            The type of the source code item generated by this rule. E.g. JMethod.
 * @param <M>
 *            The type of the meta data that this rule should operate on. E.g ApiControllerMetadata.
 *
 * @author armin.weisser
 * @since 0.4.1
 */
public interface Rule<T, R, M> {

    /**
     * Add whatever Java source is required to the given generatable to
     * represent this rule.
     *
     * @param metadata
     *            The meta data from which the code is build.
     * @param generatableType
     *            A code generation construct to which this rule should be applied
     *
     * @return The newly generated source code item that was added/created as a result of applying this rule
     */
    R apply(M metadata, T generatableType);

}
