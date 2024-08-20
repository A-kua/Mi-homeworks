## Day2-HW1

先说总结：
- Standard：**完全不复用**。启动哪个Activity就创建哪个，然后入栈，变为栈顶部。

- SingleTop：**只复用栈顶**。如果栈顶就是启动的Activity，不重复创建，而是调用其onNewIntent。

- SingleTask：**复用所有**。如果Activity已经在某个任务栈中，则清空这个 Activity上面所有Activity，让其变为栈顶。

- SingleInstance：**独享栈**。每个Activity所在的任务栈都只有一个实例。

## 不同场景下的生命周期

重点关注onCreate、onDestroy以及onNewIntent。

### Standard模式

场景：启动A，启动B，启动C，启动A，启动B。

该场景下，就是单纯一味的压入实例。最终的栈视图如下：

```text
+----+
| B2 | 这里是栈顶
+----+
+----+
| A2 |
+----+
+----+
| C  |
+----+
+----+
| B1 |
+----+
+----+
| A1 |
+----+
```

### SingleTop模式

已经按如下顺序启动：A, B, C。此时栈视图如下：

```text
+----+
| C  | 这里是栈顶
+----+
+----+
| B  |
+----+
+----+
| A  |
+----+
```

场景1：再启动C

```text
+----+
| C  | 这里是栈顶
+----+
+----+
| B  |
+----+
+----+
| A  |
+----+
```

栈不变，C被复用，同时调用了onNewIntent。
具体来说生命周期调用顺序为：

onPause -> onNewIntent -> onResume

场景2：再启动B

```text
+----+
| B2 | 这里是栈顶
+----+
+----+
| C  | 
+----+
+----+
| B  |
+----+
+----+
| A  |
+----+
```

此时因为B不在栈顶，所以不复用，而是创建一个新的B实例压栈。

### SingleTask模式

已经按如下顺序启动：A, B, C。此时栈视图如下：

```text
+----+
| C  | 这里是栈顶
+----+
+----+
| B  |
+----+
+----+
| A  |
+----+
```

场景：启动B

```text
+----+
| B  | 这里是栈顶
+----+
+----+
| A  |
+----+
```

因为B在栈中存在，所以B返回栈顶，同时调用其onNewIntent方法。
具体来说生命周期调用顺序为：

C.onPause -> B.onStart -> B.onNewIntent -> B.onResume -> C.onStop -> C.onDestroy

### SingleInstance模式

已经按如下顺序启动：A, B, C。此时栈视图如下：

```text
+----+
| C  | 这里是栈顶
+----+
+----+
| B  |
+----+
+----+
| A  |
+----+
```

场景：启动D

```text
+----+    +----+
| C  |    | D  |
+----+    +----+
+----+
| B  |
+----+
+----+
| A  |
+----+
```

D为独立的栈，且这个栈只能有唯一的实例。

其余详细的生命周期变化请见

[任务2](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/blob/main/day2/Day2-Train2.md)