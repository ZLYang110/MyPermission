# MyPermission

Android轻量级权限申请。简单方便

运行时权限、未知应用安装权限、悬浮窗权限、显示通知和访问通知权限


[GitHub主页](https://github.com/ZLYang110/MyPermission)

[Demo下载](https://github.com/ZLYang110/MyPermission/raw/master/app/release/app-release.apk)

[简书](https://www.jianshu.com/p/3c64052e6237)


# 简介

对[AndPermission](https://github.com/yanzhenjie/AndPermission)的封装。链式调用，简单方便。

- 运行时权限
- 未知应用安装权限
- 悬浮窗权限
- 显示通知权限
- 访问通知权限

在申请多个权限时，本框架采用排队方式申请，即先申请第一个权限，第一个申请成功后再进行下一个的申请流程，第一个失败则为本次申请失败。

# 运行截图
<img src="https://github.com/ZLYang110/MyPermission/blob/master/screenshot/Screenshot_20200503.jpg" width = "180" height = "300" alt="图片名称"   /> <img src="https://github.com/ZLYang110/MyPermission/blob/master/screenshot/Screenshot_20200503_164747.jpg" width = "180" height = "300" alt="图片名称"   /> <img src="https://github.com/ZLYang110/MyPermission/blob/master/screenshot/Screenshot_20200503_164735.jpg" width = "180" height = "300" alt="图片名称"  />


# 使用说明

## 集成


- ### 添加jitpack库

```java
// build.gradle(Project:)
allprojects {
    repositories {
        ...
            maven { url 'https://www.jitpack.io' }
    }
}
```

- ### 添加依赖

```groovy
// build.gradle(Module:)
dependencies {

   implementation 'com.github.ZLYang110:MyPermission:1.0'
}
```

### 运行时权限


```java

 PermissionUtils.request(new RequestListener() {
            @Override
            public void onSuccess() {
                // TODO 授权成功
                Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed() {
                // TODO 授权失败
                Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
            }
        },MainActivity.this,REQ_CODE_PERMISSION,Manifest.permission.CAMERA);

```


### 安装未知权限


```java

 PermissionUtils.requestInstall(new RequestListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
            }
        },MainActivity.this,new File(MainActivity.this.getCacheDir(), "test.apk").getPath());

```


### 悬浮窗权限


```java

  PermissionUtils.requestOverlay(new RequestListener() {
             @Override
             public void onSuccess() {
                 Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
             }
             @Override
             public void onFailed() {
                 Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
             }
         },MainActivity.this);
```

### 设置权限


```java

   PermissionUtils.requestSetting(new RequestListener() {
              @Override
              public void onSuccess() {
                  Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
              }
              @Override
              public void onFailed() {
                  Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
              }
          },MainActivity.this);

```

### 显示通知权限


```java

  PermissionUtils.requestNotificationShow(new RequestListener() {
              @Override
              public void onSuccess() {
                  Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
              }
              @Override
              public void onFailed() {
                  Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
              }
          },MainActivity.this);

```

### 访问通知权限


```java

  PermissionUtils.requestNotificationAccess(new RequestListener() {
              @Override
              public void onSuccess() {
                  Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
              }
              @Override
              public void onFailed() {
                  Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
              }
          },MainActivity.this);

```

# 更新日志

1.0
----

  - Android轻量级权限申请

