package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ResumeTestData {
    private Storage storage;

    private static final String UUID_1 = "78c4af01-1a12-4728-985a-d456f4d74501";
    private static final String UUID_2 = "78c4af01-1a12-4728-985a-d456f4d74512";
    private static final String UUID_3 = "78c4af01-1a12-4728-985a-d456f4d74523";

    static final Resume RESUME_1;
    static final Resume RESUME_2;
    static final Resume RESUME_3;

    public ResumeTestData(Storage storage) {
        this.storage = storage;
    }

    static {
        RESUME_2 = new Resume(UUID_2, "name2");
        RESUME_3 = new Resume(UUID_3, "name3");

        RESUME_1 = new Resume("Кислин Григорий");
     /*   RESUME_1.setContacts(ContactType.PHONE, "+7(921) 855-0482");
        RESUME_1.setContacts(ContactType.SKYPE, "grigory.kislin");
        RESUME_1.setContacts(ContactType.MAIL, "gkislin@yandex.ru");
        RESUME_1.setContacts(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        RESUME_1.setContacts(ContactType.GITHUB, "https://github.com/gkislin");
        RESUME_1.setContacts(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        RESUME_1.setContacts(ContactType.HOME, "http://gkislin.ru/");
        RESUME_1.setSections(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        RESUME_1.setSections(SectionType.PERSONAL, new TextSection("Аналитичский склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        RESUME_1.setSections(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.")));
        RESUME_1.setSections(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,",
                "MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов",
                "проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\"")));

        RESUME_1.setSections(SectionType.EXPERIENCE, new OrganizationSection(Arrays.asList(new Organization("Java Online Projects", "http://javaops.ru/",
                        Collections.singletonList(new OrganizationPeriod(2013, 10, LocalDate.now(), "Автор проекта.",
                                "Создание, организация и проведение Java онлайн проектов и стажировок."))),

                new Organization("Wrike", "https://www.wrike.com/", Collections.singletonList(new OrganizationPeriod(2014, 10,
                        2016, 1, "Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))),
                new Organization("RIT Center", null, Collections.singletonList(new OrganizationPeriod(2012, 4,
                        2014, 10, "Java архитектор",
                        "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"))),
                new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/", Collections.singletonList(new OrganizationPeriod(2010, 12,
                        2012, 4, "Ведущий программист",
                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."))),
                new Organization("Yota", "https://www.yota.ru/", Collections.singletonList(new OrganizationPeriod(2008, 6,
                        2010, 12, "Ведущий специалист",
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"))),
                new Organization("Enkata", "http://enkata.com/", Collections.singletonList(new OrganizationPeriod(2007, 3,
                        2008, 6, "Разработчик ПО",
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."))),
                new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html", Collections.singletonList(new OrganizationPeriod(2005, 1,
                        2007, 2, "Разработчик ПО",
                        "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."))),
                new Organization("Alcatel", "http://www.alcatel.ru/", Collections.singletonList(new OrganizationPeriod(1997, 9,
                        2005, 1, "Инженер по аппаратному и программному тестированию",
                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))))));

        RESUME_1.setSections(SectionType.EDUCATION, new OrganizationSection(Arrays.asList(new Organization("Coursera", "https://www.coursera.org/course/progfun",
                        Collections.singletonList(new OrganizationPeriod(2013, 3, 2013, 5,
                                "\"Functional Programming Principles in Scala\" by Martin Odersky", null))),
                new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                        Collections.singletonList(new OrganizationPeriod(2011, 3, 2011, 4,
                                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null))),
                new Organization("Siemens AG", "http://www.siemens.ru/", Collections.singletonList(new OrganizationPeriod(2005, 1,
                        2005, 4, "3 месяца обучения мобильным IN сетям (Берлин)", null))),
                new Organization("Alcatel", "http://www.alcatel.ru/", Collections.singletonList(new OrganizationPeriod(1997, 9,
                        1998, 3, "6 месяцев обучения цифровым телефонным сетям (Москва)", null))),
                new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
                        Arrays.asList(new OrganizationPeriod(1993, 9, 1996, 7, "Аспирантура (программист С, С++)", null),
                                new OrganizationPeriod(1987, 9, 1993, 7,
                                        "Инженер (программист Fortran, C)", null))),
                new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/", Collections.singletonList(new OrganizationPeriod(1984, 9,
                        1987, 1, "Закончил с отличием", null))))));
   */
    }
}