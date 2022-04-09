# Ejercicio1Proyecto
//Último intento de Myservlet (no da error pero no muestra el desplgable al dar a buscar).
//Los archivos no dan errores y el servidor carga pero no me la vista desplegable al dar en buscar. Creo que es porque no mapea la URL.
//Añado los cambios que he hecho en MyServlet por intuición. No sé si tengo que crear algo fuera de los archivos que temando.

<?xml version="1.0" encoding="UTF-8"?>
<beans
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://www.springframework.org/schema/beans"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context-3.0.xsd
                           http://www.springframework.org/schema/tx
                           http://www.springframework.org/schema/tx/spring-tx-3.0.xsd"
	default-lazy-init="true">

	<bean id="urlMapping"
		class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
		<property name="mappings">
			<props>
				<prop key="/test/*.test">controller</prop>
				<prop key="/paciente/*.test">controllerPaciente</prop>
			</props>
		</property>
	</bean>

	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.XmlViewResolver">
		<property name="location">
			<value>/WEB-INF/MyViews.xml</value>
		</property>
	</bean>

	<bean id="controller"
		class="org.springframework.web.servlet.mvc.multiaction.MultiActionController">
		<property name="methodNameResolver">
			<ref bean="MyMethodNameResolver" />
		</property>
		<property name="delegate">
			<ref bean="generalController" />
		</property>
	</bean>

	<bean id="controllerPaciente"
		class="org.springframework.web.servlet.mvc.multiaction.MultiActionController">
		<property name="methodNameResolver">
			<ref bean="PacienteNameResolver" />
		</property>
		<property name="delegate">
			<ref bean="pacienteController" />
		</property>
	</bean>

	<bean id="MyMethodNameResolver"
		class="org.springframework.web.servlet.mvc.multiaction.PropertiesMethodNameResolver">
		<property name="mappings">
			<props>
				<prop key="/test/agendaUrgencias.test">agendaUrgencias</prop>
			</props>
		</property>
	</bean>
	
	<bean id="PacienteNameResolver"
		class="org.springframework.web.servlet.mvc.multiaction.PropertiesMethodNameResolver">
		<property name="mappings">
			<props>
				<prop key="/paciente/getPacientes.test">getPacientes</prop>
			</props>
		</property>
	</bean>

	<bean id="generalController"
		class="com.hphis.controller.GeneralController">
		<property name="checkService">
			<ref bean="checkService" />
		</property>
	</bean>

	<bean id="pacienteController"
		class="com.hphis.controller.PacienteController">
		<property name="pacienteService">
			<ref bean="pacienteService" />
		</property>
	</bean>

	<bean id="checkService"
		class="com.hphis.service.impl.CheckServiceImpl">
		<property name="checkDao">
			<ref bean="checkDao" />
		</property>
	</bean>

	<bean id="checkDao" class="com.hphis.dao.impl.CheckDaoImpl" />

	<bean id="pacienteService"
		class="com.hphis.service.impl.PacienteServiceImpl">
		<property name="pacienteDao">
			<ref bean="pacienteDao" />
		</property>
	</bean>

	<bean id="pacienteDao" class="com.hphis.dao.impl.PacienteDaoImpl" />

</beans>
