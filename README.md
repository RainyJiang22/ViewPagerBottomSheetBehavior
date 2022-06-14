# ViewPagerBottomSheetBehavior

resolve the bottomSheetDialog have more than one fragments do not scrolling child bug

## How to Use

- Add it in your root build.gradle at the end of repositories

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- Add the dependency

```groovy
dependencies {
    implementation 'com.github.RainyJiang22:ViewPagerBottomSheetBehavior:v1.0.0'
}
```

- you should make your BottomSheetFragment to extends the `BaseSheetDialogFragment` and
  interface `CallBackScrollChild`,like this

```kotlin
class ContentSheetFragment :
    BaseSheetDialogFragment<FragmentContentBinding, EmptyViewModel>(),
    CallBackScrollChild
```

- it have override a function,make the behavior to invalidate

```kotlin
  override fun backScrollChild(scrollChild: View) {
    behavior?.invalidateScrollingChild(scrollChild)
}
```

- Then,if your have more fragments ,every fragment should have this interface callback , At the
  fragment's onVisible to change the scrollChildView(recyclerView/listView/other scroll view),like
  this

```kotlin
override fun onVisible() {
    super.onVisible()
    binding?.rvFirst?.let { callBackScrollChild?.backScrollChild(it) }
}
```

**If you have know more about this project ,please Check On and look the example**

