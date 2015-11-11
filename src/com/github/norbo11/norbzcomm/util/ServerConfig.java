package com.github.norbo11.norbzcomm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.github.norbo11.norbzcomm.settings.chatrooms.ChatRoomsCategory;

public class ServerConfig {
    public static final String DEFAULT_PATH = "ServerConfig.xml";
    private static Document doc;

    public static void loadConfig() {
        MessengerManager.reset();
        try {
            doc = new Document(new Element("server"));
            SAXBuilder builder = new SAXBuilder();
            doc = builder.build(new File(DEFAULT_PATH));

            List<Element> chats = doc.getRootElement().getChild("chatrooms").getChildren();
            if (chats != null) {
                for (Element chat : chats) {
                    Chat chatObject = new Chat();
                    chatObject.setName(chat.getChildText("name"));
                    chatObject.setOwner(chat.getChildText("owner"));
                    for (Element op : chat.getChildren("operators")) {
                        if (op.getChildText("op") != null) chatObject.getOperators().add(op.getChildText("op"));
                    }
                    MessengerManager.addChat(chatObject);
                }
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createConifg() {
        File file = new File(DEFAULT_PATH);
        
        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        Element chats = new Element("chatrooms");
        for (Object chatObject : ChatRoomsCategory.getListModel().toArray()) {
            Chat chat = (Chat) chatObject;

            Element element = new Element("chat");
            element.addContent(new Element("name").addContent(chat.getName()));
            element.addContent(new Element("owner").addContent(chat.getOwner()));
            Element ops = new Element("operators");
            for (String op : chat.getOperators())
            {
                ops.addContent(new Element("op").addContent(op));
            }
            element.addContent(ops);
            chats.addContent(element);
        }

        Document doc = new Document(new Element("server").addContent(chats));

        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        try {
            out.output(doc, new FileOutputStream(new File(DEFAULT_PATH)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
