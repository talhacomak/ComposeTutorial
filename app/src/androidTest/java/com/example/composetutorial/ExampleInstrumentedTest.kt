package com.example.composetutorial

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.composetutorial", appContext.packageName)
    }

    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun myTest() {
        // Start the app
        composeTestRule.setContent {
            @Composable
            fun ContentPreview() {
                ComposeTutorialTheme {
                    // ...

                    val colorBoxNode = composeTestRule.onNodeWithText("Continue")

                    // Get the zIndex value from the SemanticsNode
                    val zIndex = colorBoxNode.fetchSemanticsNode().config

                    // Print the zIndex value
                    println("zIndex: $zIndex")
                }
            }
        }
    }
}