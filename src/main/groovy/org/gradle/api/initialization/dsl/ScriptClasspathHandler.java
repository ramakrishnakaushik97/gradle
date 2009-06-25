/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.initialization.dsl;

import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.artifacts.ConfigurationContainer;
import groovy.lang.Closure;

/**
 * <p>A {@code ScriptClasspathHandler} allows you to manage the classpath used to compile and execute a build
 * script.</p>
 *
 * <p>To declare the script classpath, you use the {@link org.gradle.api.artifacts.dsl.DependencyHandler} provided by
 * {@link #getDependencies()} to attach dependencies to the {@value #CLASSPATH_CONFIGURATION} configuration. These
 * dependencies are resolved just prior to script compilation, and assembled into the classpath for the script.</p>
 *
 * <p>For most external dependencies you will also need to declare one or more repositories where the dependencies can
 * be found, using the {@link org.gradle.api.artifacts.dsl.RepositoryHandler} provided by {@link
 * #getRepositories()}.</p>
 */
public interface ScriptClasspathHandler {
    /**
     * The name of the configuration used to assemble the script classpath.
     */
    String CLASSPATH_CONFIGURATION = "classpath";

    /**
     * Returns a handler to create repositories which are used for retrieving dependencies for the script classpath.
     *
     * @return the repository handler. Never returns null.
     */
    RepositoryHandler getRepositories();

    /**
     * Configures the repositories for the script dependencies. Executes the given closure against the {@link
     * RepositoryHandler} for this handler. The {@link RepositoryHandler} is passed to the closure as the closure's
     * delegate.
     *
     * @param configureClosure the closure to use to configure the repositories.
     */
    void repositories(Closure configureClosure);

    /**
     * Returns the dependencies of the script. The returned dependency handler instance can be used for adding new
     * dependencies. For accessing already declared dependencies, the configurations can be used.
     *
     * @return the dependency handler. Never returns null.
     * @see #getConfigurations()
     */
    DependencyHandler getDependencies();

    /**
     * Configures the dependencies for the script. Executes the given closure against the {@link DependencyHandler} for
     * this handler. The {@link DependencyHandler} is passed to the closure as the closure's delegate.
     *
     * @param configureClosure the closure to use to configure the dependencies.
     */
    void dependencies(Closure configureClosure);

    /**
     * Returns the configurations of this handler. This usually contains a single configuration, called {@value
     * #CLASSPATH_CONFIGURATION}.
     *
     * @return The configuration of this handler.
     */
    ConfigurationContainer getConfigurations();
}
