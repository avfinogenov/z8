package org.zenframework.z8.server.runtime;

import org.zenframework.z8.server.base.job.scheduler.Jobs;
import org.zenframework.z8.server.base.job.scheduler.TaskLogs;
import org.zenframework.z8.server.base.job.scheduler.Tasks;
import org.zenframework.z8.server.base.table.system.Entries;
import org.zenframework.z8.server.base.table.system.Files;
import org.zenframework.z8.server.base.table.system.Properties;
import org.zenframework.z8.server.base.table.system.Property;
import org.zenframework.z8.server.base.table.system.SecurityGroups;
import org.zenframework.z8.server.base.table.system.Sequences;
import org.zenframework.z8.server.base.table.system.SystemTools;
import org.zenframework.z8.server.base.table.system.UserEntries;
import org.zenframework.z8.server.base.table.system.Users;
import org.zenframework.z8.server.ie.ExportMessages;

public class ServerRuntime extends AbstractRuntime {

    public static final Property PreserveExportMessagesProperty = new Property("8D9C727A-34FC-4DCD-9AB0-5A2AF8E676E0",
            "transportPreserveExportMessages", "false", "Сохранять локальную очередь экспортируемых сообщений");
    public static final Property EnableProtocolsProperty = new Property("222A95B9-05BC-4AF3-8425-323D8B1A1B73",
            "enableProtocols", "", "Список (через ',') протоколов, инициализируемых при старте");
    public static final Property SelfAddressDefaultProperty = new Property("7370AF2A-AA31-49E7-84AA-E000DAF78235",
            "selfAddressDefault", "", "Адрес по умолчанию");
    public static final Property FolderProperty = new Property("3D524DE7-0AF3-40FE-8FF7-C4A073D1F834",
            "transportFileFolder", "C:/z8/transport", "Каталог для обмена по протоколу file");
    public static final Property ConnectionFactoryProperty = new Property("5CACA325-B353-43C0-917C-B9A20C21C64E",
            "jmsConnectionFactory", "org.apache.activemq.jndi.ActiveMQInitialContextFactory",
            "Класс-фабрика для получения JMS-соединений");
    public static final Property ConnectionUrlProperty = new Property("7CAF19ED-71A5-480F-A2D3-95C5B22B4CA6",
            "jmsConnectionUrl", "tcp://localhost:61616?trace=false&soTimeout=60000", "URL для подключения к JMS-серверу");
    public static final Property FileItemSizeThresholdProperty = new Property("CDF0A743-F95F-4235-AD3D-D40F589A68DF",
            "fileItemSizeThreshold", "10485760", "Порог выгрузки файла на диск (по умолчанию 10М)");

    public ServerRuntime() {
        addTable(new Users.CLASS<Users>(null));
        addTable(new SecurityGroups.CLASS<SecurityGroups>(null));
        addTable(new Sequences.CLASS<Sequences>(null));
        addTable(new Entries.CLASS<Entries>(null));

        addTable(new UserEntries.CLASS<UserEntries>(null));

        addTable(new Jobs.CLASS<Jobs>(null));
        addTable(new Tasks.CLASS<Tasks>(null));
        addTable(new TaskLogs.CLASS<TaskLogs>(null));

        addTable(new Files.CLASS<Files>(null));
        addTable(new Properties.CLASS<Properties>(null));

        addTable(new ExportMessages.CLASS<ExportMessages>(null));

        addEntry(new SystemTools.CLASS<SystemTools>(null));

        addProperty(PreserveExportMessagesProperty);
        addProperty(EnableProtocolsProperty);
        addProperty(SelfAddressDefaultProperty);
        addProperty(FolderProperty);
        addProperty(ConnectionFactoryProperty);
        addProperty(ConnectionUrlProperty);
        addProperty(FileItemSizeThresholdProperty);
    }

}