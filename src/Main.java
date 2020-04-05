import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class Main {
    public static void main(String[] args) {
        TelegramBot bot = new TelegramBot("1091538928:AAHJeExo2EX3C66-jS4Ew3I6GfBNDyj_teo");
        bot.setUpdatesListener(updates -> {
            for (Update update: updates) {
                long chatId = update.message().chat().id();

                if (update.message().text().equals("/start")) {
                    bot.execute(new SendMessage(chatId, sayGreeting()));
                    continue;
                }

                String[] strings = update.message().text().split(" ");

                if (strings.length != 2 && strings.length != 1){
                    bot.execute(new SendMessage(chatId, sayAboutInputError()));
                    continue;
                }

                WeatherSetter weatherSetter = weather -> {
                    if (weather == null){
                        bot.execute(new SendMessage(chatId, sayAboutInputError()));
                        return;
                    }

                    String stringBuilder = "Погода в городе " + strings[0];

                    if (strings.length == 2)
                        stringBuilder += " в стране " + strings[1];

                    stringBuilder += "\nТемпература: " + weather.getTemperature() + "°C\n" +
                            "Облачность: " + weather.getClouds() + "\n" +
                            "Скорость ветра: " + weather.getWindSpeed() + " м/с\n" +
                            "Влажность: " + weather.getHumidity() + "%";

                    bot.execute(new SendMessage(chatId, stringBuilder));
                };

                URLConnectionReader urlReader;

                if(strings.length == 2)
                    urlReader = new URLConnectionReader(strings[0], strings[1], weatherSetter);
                else
                    urlReader = new URLConnectionReader(strings[0], weatherSetter);

                urlReader.start();
            }

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public static String sayGreeting(){
        return "Здравствуйте,\n" +
                "WeatherBot показывает текущую погоду в городе, " +
                "для этого отправте сообщение в формате: \"город\" или \"город страна\"";
    }

    public static String sayAboutInputError(){
        return "У вас неправильный формат ввода\n" +
                "Нужный формат ввода: \"город\" или \"город страна\"";
    }
}
