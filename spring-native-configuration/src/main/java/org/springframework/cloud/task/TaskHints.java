/*
 * Copyright 2020 the original author or authors.
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
package org.springframework.cloud.task;

import java.sql.DatabaseMetaData;

import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;
import org.springframework.boot.jdbc.AbstractDataSourceInitializer;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.cloud.task.batch.handler.TaskJobLauncherApplicationRunner;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.configuration.SimpleTaskAutoConfiguration;
import org.springframework.cloud.task.repository.support.TaskRepositoryInitializer;
import org.springframework.nativex.extension.NativeImageConfiguration;
import org.springframework.nativex.extension.NativeImageHint;
import org.springframework.nativex.extension.ProxyInfo;
import org.springframework.nativex.extension.ResourcesInfo;
import org.springframework.nativex.extension.TypeInfo;
import org.springframework.nativex.type.AccessBits;

@NativeImageHint(trigger= EnableTask.class,
		resourcesInfos = { 
			@ResourcesInfo(patterns = { 
				"org/springframework/cloud/task/schema-h2.sql"
			}),
		},
		typeInfos = {
		@TypeInfo(types = {
			AbstractDataSourceInitializer.class,
		}, access=AccessBits.LOAD_AND_CONSTRUCT|AccessBits.DECLARED_METHODS),
		@TypeInfo(types = {
			DefaultTaskConfigurer.class,
			JobLauncherApplicationRunner.class,
			TaskJobLauncherApplicationRunner.class,
			DataSourcePoolMetadataProvider.class,
			SimpleTaskAutoConfiguration.class,
			TaskRepositoryInitializer.class
		}, access=AccessBits.LOAD_AND_CONSTRUCT_AND_PUBLIC_METHODS),
		@TypeInfo(types= {DatabaseMetaData.class})},
		proxyInfos = {
		@ProxyInfo(typeNames = {
				"org.springframework.cloud.task.repository.TaskRepository",
				"org.springframework.aop.SpringProxy",
				"org.springframework.aop.framework.Advised",
				"org.springframework.core.DecoratingProxy"}),
		@ProxyInfo(typeNames = {
				"org.springframework.cloud.task.repository.TaskExplorer",
				"org.springframework.aop.SpringProxy",
				"org.springframework.aop.framework.Advised",
				"org.springframework.core.DecoratingProxy"}),
		@ProxyInfo(typeNames = {
				"org.springframework.transaction.PlatformTransactionManager",
				"org.springframework.aop.SpringProxy",
				"org.springframework.aop.framework.Advised",
				"org.springframework.core.DecoratingProxy"}),
		@ProxyInfo(typeNames = {
				"java.util.concurrent.ConcurrentMap", 
				"java.io.Serializable",
				"java.util.Map",
				"org.springframework.aop.SpringProxy",
				"org.springframework.aop.framework.Advised",
				"org.springframework.core.DecoratingProxy"})
		})
public class TaskHints implements NativeImageConfiguration { }