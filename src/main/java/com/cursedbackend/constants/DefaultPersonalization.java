package com.cursedbackend.constants;

import java.util.List;

import com.cursedbackend.entities.PersonalizationConfiguration;
import com.cursedbackend.entities.PersonalizationWallpapers;
import com.cursedbackend.entities.enums.ShortcutType;

public class DefaultPersonalization {
    public static final List<PersonalizationConfiguration> defaultConfigs = List.of(
            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("ChatGPT")
                    .url("https://chatgpt.com/")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/chatgpt.png")
                    .rank("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Claude")
                    .url("https://claude.ai/")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/claude.png")
                    .rank("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Hetzner Console")
                    .url("https://console.hetzner.com/")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/hetzner.svg")
                    .rank("cccccccccccccccccccccccccccccccc")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("GitHub")
                    .url("https://github.com")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/github-mark-white.png")
                    .rank("dddddddddddddddddddddddddddddddd")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Gitlab")
                    .url("https://gitlab.com/")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/gitlab-logo-500.png")
                    .rank("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Discord")
                    .url("https://discord.com/channels/@me")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/discord.svg")
                    .rank("ffffffffffffffffffffffffffffffff")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Json Formatter")
                    .url("https://jsonformatter.sudox1.com")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/json.png")
                    .rank("gggggggggggggggggggggggggggggggg")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Youtube")
                    .url("https://youtube.com")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/youtube.png")
                    .rank("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Youtube Music")
                    .url("https://music.youtube.com")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/youtube-music.png")
                    .rank("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Prime Video")
                    .url("https://www.primevideo.com/")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/primeVideo.png")
                    .rank("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Jira")
                    .url("https://zentrumhub.atlassian.net/jira/software/c/projects/NEX/boards/1")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/jira.png")
                    .rank("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Amplify")
                    .url("https://ap-south-1.console.aws.amazon.com/amplify/apps")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/Amplify.png")
                    .rank("llllllllllllllllllllllllllllllll")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("DynamoDB")
                    .url("https://ap-south-1.console.aws.amazon.com/dynamodbv2/home?region=ap-south-1#dashboard")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/DynamoDB.png")
                    .rank("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("S3 Bucket")
                    .url("https://ap-south-1.console.aws.amazon.com/s3/home?region=ap-south-1#")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/SimpleStorageService.png")
                    .rank("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn")
                    .build(),

            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Leetcode 50")
                    .url("/leetcode50.md")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/leetcode.png")
                    .rank("oooooooooooooooooooooooooooooooo")
                    .build(),

            // Duplicate intentionally preserved
            PersonalizationConfiguration.builder()
                    .userEmail(Constants.DEFAULT_USER_EMAIL)
                    .type(ShortcutType.WEB)
                    .name("Leetcode 50")
                    .url("/leetcode50.md")
                    .image("https://cdn.sudox1.com/uploadnpray/users/personalization/shortcuts/leetcode.png")
                    .rank("pppppppppppppppppppppppppppppppp")
                    .build());

    public static final PersonalizationWallpapers defaultWallpapers = PersonalizationWallpapers.builder()
            .userEmail(Constants.DEFAULT_USER_EMAIL)
            .wallpaperUrls(List.of(
                    "https://cdn.sudox1.com/uploadnpray/users/personalization/wallpapers/captain-shinsui-kyoraku.png",
                    "https://cdn.sudox1.com/uploadnpray/users/personalization/wallpapers/comet.jpg",
                    "https://cdn.sudox1.com/uploadnpray/users/personalization/wallpapers/gojo-max-real.jpeg",
                    "https://cdn.sudox1.com/uploadnpray/users/personalization/wallpapers/gotei-4k.jpg",
                    "https://cdn.sudox1.com/uploadnpray/users/personalization/wallpapers/Itachi-uchiha.png",
                    "https://cdn.sudox1.com/uploadnpray/users/personalization/wallpapers/makima1.png",
                    "https://cdn.sudox1.com/uploadnpray/users/personalization/wallpapers/makima2.png",
                    "https://cdn.sudox1.com/uploadnpray/users/personalization/wallpapers/midnight-reflections-moonlit-sea.jpg"))
            .build();
}
